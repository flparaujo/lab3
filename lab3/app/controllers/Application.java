package controllers;

import models.FormHandler;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {
	
	static SistemaDePlanejamentoDeCurso sistema = new SistemaDePlanejamentoDeCurso();
	static Form<FormHandler> handlerForm = Form.form(FormHandler.class);

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	return ok(views.html.index.render(sistema, handlerForm));
    }
    
    public static Result novoPeriodo() {
    	sistema.adicionaPeriodo();
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result adicionaDisciplinaNoPeriodoAtual() {
    	Form<FormHandler> form = handlerForm.bindFromRequest();
    	int numeroDeCreditos = sistema.getDisciplinaDaGrade(form.get().getNomeDisciplina()).getNumeroDeCreditos();
    	sistema.getPeriodoAtual().adicionaDisciplina(form.get().getNomeDisciplina(), numeroDeCreditos);
    	return redirect(routes.Application.planejamentoDeCurso());
    }

}
