package inovai.market.server

class Instituicao {
    String nome
    String escopo
    String cidade

    static hasMany = [empresas: Empresa]

    static constraints = {
        nome minSize: 1, maxSize: 255, nullable: false, blank: false
        escopo minSize: 1, maxSize: 255, nullable: false, blank: false
        cidade minSize: 1, maxSize: 255, nullable: false, blank: false
        empresas nullable: true
    }
}
