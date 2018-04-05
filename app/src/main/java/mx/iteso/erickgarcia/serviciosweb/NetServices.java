package mx.iteso.erickgarcia.serviciosweb;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by erickgarcia on 04/04/18
 */

public class NetServices extends AsyncTask<String, Void, JSONObject>{
    private onTaskCompleted context;

    public NetServices(onTaskCompleted context){
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... strings) { //ah no mames que esto es un array :O
        JSONObject jsonObject = null;
        try {
            if (strings[0] == "post")
                jsonObject = new JSONObject(new RequestPost().requestPost(strings));
            if (strings[0] == "get")
                jsonObject = new JSONObject();
        } catch (Exception ex) {
            Log.e("errorWS", ex.toString());
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        if (jsonObject != null)
            context.onTaskCompleted(jsonObject.toString());
        else
            Toast.makeText((Context) context, "error en la peticion", Toast.LENGTH_SHORT).show();
    }
}
