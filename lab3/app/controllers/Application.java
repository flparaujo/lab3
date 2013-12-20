package controllers;

import form.FormHandler;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {
	
	static SistemaDePlanejamentoDeCurso sistema = new SistemaDePlanejamentoDeCurso();
	static Form<FormHandler> formHandler = Form.form(FormHandler.class);

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	return ok(views.html.index.render(sistema, formHandler));
    }
    
    public static Result novoPeriodo() {
    	sistema.adicionaPeriodo();
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result adicionaDisciplinaNoPeriodoAtual() {
    	Form<FormHandler> form = formHandler.bindFromRequest();
    	int numeroDeCreditos = sistema.getDisciplinaDaGrade(form.get().getInputNameDisciplina()).getNumeroDeCreditos();
    	sistema.adicionaDisciplinaAoPeriodoAtual(form.get().getInputNameDisciplina(), numeroDeCreditos);
    	return redirect(routes.Application.planejamentoDeCurso());
    }

}
