package inovai.market.server


import grails.rest.*
import grails.converters.*

class EmpresaController extends RestfulController<Empresa>{
	static responseFormats = ['json', 'xml']

    EmpresaController() {
        super(Empresa)
    }
}
