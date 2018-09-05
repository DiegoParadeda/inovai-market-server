package inovai.market.server

class Produto {
    String nome
    String cidade
    String escopo

    static belongsTo = [empresa: Empresa]

    static constraints = {
        nome minSize: 1, maxSize: 255, nullable: false, blank: false
        escopo minSize: 1, maxSize: 255, nullable: false, blank: false
        cidade minSize: 1, maxSize: 255, nullable: false, blank: false
        empresa nullable: false
    }
}