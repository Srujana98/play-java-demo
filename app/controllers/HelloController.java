package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class HelloController extends Controller {
    Map<String,String> map1=new HashMap<String,String>();
    public Result doSomething(){
          return ok("hello from fancy");
    }

    public Result helloUser(String name){
        String message="Hello "+name+"!";
        return ok(message);
    }


    public Result helloUserWithDetails(){
        JsonNode requestJson=request().body().asJson();
        String firstName=null;
        String lastName=null;
        if(requestJson.has("first_name"));
        {
            firstName=requestJson.get("first_name").asText();
        }
        if(requestJson.has("last_name"));
        {
            lastName=requestJson.get("last_name").asText();
        }
        if(lastName!="" && firstName!="")
        {
            String message="Hello "+firstName+" "+lastName;
            return ok(message);
        }

        return badRequest("provide fields");
    }

    public Result storeDetails()
    {
        JsonNode reqJson=request().body().asJson();
        String id=null;
        String name=null;
        if(reqJson.has("Uid")){
            id=reqJson.get("Uid").asText();
        }
        if(reqJson.has("Uname"))
        {
            name=reqJson.get("Uname").asText();
        }
        if(id!=null && name!=null)
        {

            map1.put(id,name);
            return ok("Hello "+name);
        }
        return badRequest("enter details");

    }
    public Result retDetails(String id)
    {
        if(map1.get(id)!=null) {
            String name1 = map1.get(id);
            return ok("Your name is " + name1);
        }
        if(map1.get(id)==null){
             return ok("no rows with id "+id);
        }
        return badRequest("Error with field name");
    }





}
