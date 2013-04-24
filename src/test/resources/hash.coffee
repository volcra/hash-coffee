require = () ->
coffee = () ->
    print "compiling coffee -> #{arguments}"

compress = () ->
    print "compressing -> #{arguments}"

uglify = () ->

require 'coffee'
require 'compress'

task = () ->
    params = if arguments then arguments else []
    (f) ->
        print "from #{params}running f"
        f()

task('coffee') ->
   coffee(filter '**/*.coffee', (f) -> f.name == 'rettsts') -> compress() -> uglify()

task('coffee-compress') ->
   coffee(filter '**/*.coffee', (f) -> f.name == 'rettsts') -> compress() -> uglify()

task('coffee-compress', 'description') ->
   coffee(filter '**/*.coffee', (f) -> f.name == 'rettsts') -> compress() -> uglify()

task(name: 'coffee', description: 'coffee runner') ->
  compile resources
  compress resources
  print "done"

task('maven') ->
    print 'maven'

# apply function to every element and return the new elements
# map

# combine everything into one result
# reduce

# loops over a list
# forEach

resource = (source, target) ->
    r = () ->
        source

    r.select = (f) ->
        resource((r for r in source when f r), target)

    r.reduce = () ->
        resource((r for r in source).join(), target)

    r.map = (f) ->
        resource((f r for r in source), target)

    r

coffees = resource ['service.coffee', 'controller.coffee'], 'target\coffee'

print coffees
print coffees()
print coffees.select((r) -> r.indexOf('service') isnt -1)()
print coffees.reduce()()
print coffees.select((r) -> r.indexOf('service') isnt -1).reduce()()
print coffees.map((r) -> r + ".js").reduce()()
coffees.map(coffee).map(compress)