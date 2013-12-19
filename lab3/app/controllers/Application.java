package controllers;

import play.data.Form;
import play.mvc.*;

public class Application extends Controller {
	
	static SistemaDePlanejamentoDeCurso sistema = new SistemaDePlanejamentoDeCurso();
	static Form<Teste> taskFormTeste = Form.form(Teste.class);

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	return ok(views.html.index.render(sistema, taskFormTeste));
    }
    
    public static Result novoPeriodo() {
    	sistema.adicionaPeriodo();
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result adicionaDisciplinaNoPeriodoAtual() {
    	Form<Teste> form = taskFormTeste.bindFromRequest();
    	int numeroDeCreditos = sistema.getDisciplinaDaGrade(form.get().teste).getNumeroDeCreditos();
    	sistema.getPeriodoAtual().adicionaDisciplina(form.get().teste, numeroDeCreditos);
    	return redirect(routes.Application.planejamentoDeCurso());
    }

}
