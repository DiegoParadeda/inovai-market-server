package inovai.market.server

class Empresa {
    String nome
    String cidade
    String escopo

    static hasMany = [produtos: Produto]

    static constraints = {
        nome minSize: 1, maxSize: 255, nullable: false, blank: false
        escopo minSize: 1, maxSize: 255, nullable: false, blank: false
        cidade minSize: 1, maxSize: 255, nullable: false, blank: false
        produtos nullable: true
    }
}
