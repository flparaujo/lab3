package controllers;

import play.data.Form;
import play.mvc.*;

public class Application extends Controller {
	
	static SistemaDePlanejamentoDeCurso sistema = new SistemaDePlanejamentoDeCurso();
	static Form<SistemaDePlanejamentoDeCurso> taskForm = Form.form(SistemaDePlanejamentoDeCurso.class);

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	return ok(views.html.index.render(sistema, taskForm));
    }
    
    public static Result novoPeriodo() {
    	sistema.adicionaPeriodo();
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result adicionaDisciplinaNoPeriodoAtual(String nome) {
    	int numeroDeCreditos = sistema.getDisciplinaDaGrade(nome).getNumeroDeCreditos();
    	sistema.getPeriodoAtual().adicionaDisciplina(nome, numeroDeCreditos);
    	return redirect(routes.Application.planejamentoDeCurso());
    }

}
