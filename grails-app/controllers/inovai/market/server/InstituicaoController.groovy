package inovai.market.server


import grails.rest.*
import grails.converters.*

class InstituicaoController extends RestfulController<Instituicao>{
	static responseFormats = ['json', 'xml']

    InstituicaoController() {
        super(Instituicao)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render data: listAllResources(params) as JSON, total: super.countResources()
    }
}
