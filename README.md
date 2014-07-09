# bitspoke-www

Public website of Bitspoke Ltd written using Play, Scala, MongoDB, Javascript and AngularJS
 

## Development

### Tools
* [Java SDK 1.7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [TypeSafe Activator](https://typesafe.com/activator/)
* [Node.js](http://nodejs.org/) 
* [NPM](https://www.npmjs.org/) - Node Packaged Manager
* [MongoDB](http://www.mongodb.org/)  


### Database

```shell
mkdir -p data
mongod --dbpath data
```
   

### Build, Run and Debug

```shell
activator -jvm-debug 9999
```

```shell
clean
test
run
```



