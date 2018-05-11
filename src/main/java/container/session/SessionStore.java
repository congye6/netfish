package container.session;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import util.HttpFormatUtil;

import java.io.*;

/**
 * Created by cong on 2018-05-10.
 */
public class SessionStore {


    private static final String DEFUALT_DIR="session";

    private static final String FILE_FORMAT=".session";

    private String dir=DEFUALT_DIR;

    public Session load(String id){
        if(id==null)
            return null;
        File file=new File(getPath(id));
        if(!file.exists())
            return null;
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String json=reader.readLine();
            StandardSession session=JSONObject.parseObject(json,StandardSession.class);
            return session;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void save(Session session){
        if(session==null||session.getId()==null)
            return;
        String path= getPath(session.getId());
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(new File(path)));
            writer.write(JSONObject.toJSONString(session));
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    private String getPath(String id){
        return HttpFormatUtil.WEB_ROOT+"/"+dir+"/"+id+FILE_FORMAT;
    }

    public static void main(String[] args){
        SessionStore sessionStore=new SessionStore();
        StandardSession session=new StandardSession();
        session.setId("1");
        session.setValid(false);
        sessionStore.save(session);

        Session readSession=sessionStore.load("1");
        System.out.println(readSession.isValid());
    }
}
