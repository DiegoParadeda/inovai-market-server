package inovai.market.server


import grails.rest.*
import grails.converters.*
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.NO_CONTENT

class EmpresaController extends RestfulController<Empresa> {
    static responseFormats = ['json', 'xml']

    EmpresaController() {
        super(Empresa)
    }

    @Override
    def index(Integer max) {
        def list
        params.max = Math.min(max ?: 10, 100)
        if (params.search) {
            def empresaList = Empresa.createCriteria().list(params) {
                'ilike'('nome', "%${params.search}%")
            }
            render([data: empresaList, total: empresaList.totalCount] as JSON)
        } else if (params.sort == 'produtos') {
            String sql = """
                            SELECT emp
                            FROM Empresa as emp
                            ORDER BY size(emp.produtos) """.toString()

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

    @Override
    def delete() {
        def empresa = Empresa.get(params.id)
        if(!empresa)
            return respond([error: "Empresa nao encontrada"], status: HttpStatus.NOT_FOUND.value())
        def inst = Instituicao.withCriteria {
            empresas {
                idEq(empresa.id)
            }
        }
        inst.each {
            it.removeFromEmpresas(empresa)
        }

        empresa.delete(failOnError: true, flush: true)
        respond([sucess: "Empresa deletada"], status: HttpStatus.OK.value())
    }
}
