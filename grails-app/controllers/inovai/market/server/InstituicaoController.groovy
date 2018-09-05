package inovai.market.server


import grails.rest.*
import grails.converters.*

class InstituicaoController extends RestfulController<Instituicao> {
    static responseFormats = ['json', 'xml']

    InstituicaoController() {
        super(Instituicao)
    }

    @Override
    def index(Integer max) {
        def list
        params.max = Math.min(max ?: 10, 100)
        if (params.search) {
            def instituicoesList = Instituicao.createCriteria().list(params) {
                'ilike'('nome', "%${params.search}%")
            }
            render([data: instituicoesList, total: instituicoesList.totalCount] as JSON)

        } else if (params.sort == 'empresas') {
            String sql = """
                            SELECT inst
                            FROM Instituicao as inst
                            ORDER BY size(inst.empresas) """.toString()

            if (params.order == 'asc')
                sql += 'asc'
            else
                sql += 'desc'
            list = Instituicao.executeQuery(sql, [max: params.max])
            render([data: list, total: super.countResources()] as JSON)

        } else {
            list = listAllResources(params)
            render([data: list, total: super.countResources()] as JSON)

        }
    }
}