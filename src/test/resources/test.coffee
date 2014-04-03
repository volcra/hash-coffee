# testing coffeescript

class Greet
    constructor: (@who) ->

    salute: () ->
        console.log "Hello #{@who}"

g = new Greet "world"
g.salute()

$ 'body'
.click (e) ->
  $ '.box'
  .fadeIn 'fast'
  .addClass '.active'
.css 'background', 'white'