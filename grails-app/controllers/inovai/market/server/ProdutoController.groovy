package inovai.market.server


import grails.rest.*
import grails.converters.*

class ProdutoController extends RestfulController<Produto> {
    static responseFormats = ['json', 'xml']

    ProdutoController() {
        super(Produto)
    }

    @Override
    def index(Integer max) {
        def list
        def total = super.countResources()
        params.max = Math.min(max ?: 10, 100)
        if (params.search) {
            list = Produto.createCriteria().list(params) {
                'ilike'('nome', "%${params.search}%")
            }
            total = list.totalCount
        }
        else {
            list = listAllResources(params)
        }

        render(view: 'index', model: [data: list, total: total])
    }
}
