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
                [nome: "Incitonha", escopo: "Tecnologia", cidade: "Itajuba"],
                [nome: "Ypoos", escopo: "Tecnologia", cidade: "Itajuba"]
        ]

        def instituicaoList = []

        instititucoes.each { inst ->
            instituicaoList.add(new Instituicao(inst).save()) // new Instituicao(nome: 'asd', msadmasd: adslkjdask)
        }

        empresas.each {
            def empresa = new Empresa(it)
            def inst = instituicaoList.first()
            empresa.save(failOnError: true)
            inst.addToEmpresas(empresa)
            inst.save(failOnError: true)

        }
    }
    def destroy = {
    }
}
