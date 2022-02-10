# Create all certificates for mTLS

For more detailed explanation see https://codeburst.io/mutual-tls-authentication-mtls-de-mystified-11fa2a52e9cf 

* Create CA that server and client will trust
```
openssl req \
  -new \
  -x509 \
  -nodes \
  -days 365 \
  -config req.ca.conf \
  -subj '/CN=my-ca' \
  -keyout ca.key \
  -out ca.crt
```

You can check certificate content
```
openssl x509 -text -noout -in ca.crt
```

* Create server key
```
openssl genrsa -out server.key 2048
```
* Create certificate signing request (CSR) for server key for `localhost`
```
openssl req \
  -new \
  -key server.key \
  -subj '/CN=localhost' \
  -out server.csr
```
* Create signed by CA normal server certificate
```
openssl x509 \
  -req \
  -in server.csr \
  -CA ca.crt \
  -CAkey ca.key \
  -CAcreateserial \
  -days 365 \
  -out server.crt
```
* Create client key
```
openssl genrsa -out client.key 2048
```
* Create certificate signing request for client
```
openssl req \
  -new \
  -key client.key \
  -subj '/CN=curl-client' \
  -out client.csr
```
* Create client certificate from CSR
```
openssl x509 \
  -req \
  -in client.csr \
  -CA ca.crt \
  -CAkey ca.key \
  -CAcreateserial \
  -days 365 \
  -out client.crt
```
