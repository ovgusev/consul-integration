# consul-integration
Run local consul on 8500 port:

`docker run -p 8500:8500 -p 8600:8600/udp --name=consul consul agent -server -ui -bootstrap -client=0.0.0.0`