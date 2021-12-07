package frontcontroller;

/*
* Front controller is where all endpoints hit first
*
*   - this is where our middleware is going to be
*
* */

import io.javalin.Javalin;

public class FrontController {

    public FrontController(Javalin app){
        /*
         * middleware
         * */
//        app.before("/protected/*",context -> {
//            //check for authentication here
//            System.out.println("before middleware hit");
//        });
//
//        app.after("/protected/*",context -> {
//            System.out.println("After middleware hit");
//        });

        //        app.erconteror(404, context -> {
//            context.result("This is not a valid endpoint.");
//        });

/*
        app.exception(NumberFormatException.class, (e, context) -> {
            context.result("Invalid Input! Expected a number");
        });*/



        new Dispatcher(app);
    }
}
