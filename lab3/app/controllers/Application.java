package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	static SistemaDePlanejamentoDeCurso sistema = new SistemaDePlanejamentoDeCurso();

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	return ok(views.html.index.render(sistema));
    }

}
