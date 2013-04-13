# testing coffeescript

class Greet
    constructor: (@who) ->

    salute: () ->
        console.log "Hello #{@who}"

g = new Greet "world"
g.salute()