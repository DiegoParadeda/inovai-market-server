package inovai.market.server

class BootStrap {

    def init = { servletContext ->
        def instititucoes = [
                [nome: "Incit", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Incitonha", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Inovai", escopo: "Tecnologia", cidade: "Itajuba"]
        ]
        def empresas = [
                [nome: "Moontech", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Disney", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Ypoos", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Ypooooooos", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Ynpoos", escopo: "Tecnologia", cidade: "Itajuba"]
        ]
        def produtos = [
                [nome: "Website completo", escopo: "Servico", cidade: "Itajuba"],
                [nome: "Manutencao de website", escopo: "Servico", cidade: "Itajuba"],
                [nome: "Reforma de website", escopo: "Servico", cidade: "Itajuba"],
                [nome: "App mobile completo", escopo: "Servico", cidade: "Itajuba"]
        ]

        def instituicaoList = []
        def produtoList = []
        def empresaList = []

        instititucoes.each { inst ->
            instituicaoList.add(new Instituicao(inst).save()) // new Instituicao(nome: 'asd', msadmasd: adslkjdask)
        }

        empresas.eachWithIndex { it, index ->
            def empresa = new Empresa(it)
            def inst = instituicaoList.first()
            def secondInst = instituicaoList.get(1)
//            def inst = instituicaoList.last()
            empresa.save(failOnError: true)
            empresaList.add(empresa)
//            if (index < 4) {
//                inst.addToEmpresas(empresa)
//                inst.save(failOnError: true)
//            }
            if (index < 2) {
                secondInst.addToEmpresas(empresa)
                secondInst.save(failOnError: true)
            }
        }

        produtos.eachWithIndex { it, index ->
            def produto = new Produto(it)
            produtoList.add(produto.save()) // new Instituicao(nome: 'asd', msadmasd: adslkjdask)
            def emp = empresaList.get(3)
            emp.addToProdutos(produto)
            produto.empresa = emp
        }
    }
    def destroy = {
    }
}