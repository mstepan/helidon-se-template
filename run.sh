#!/bin/bash

export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4000
export DEBUG_SSL=-Djavax.net.debug=all

java ${REMOTE_DEBUGGER} -jar target/helidon-se-template.jar
