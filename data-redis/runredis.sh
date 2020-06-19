#!/bin/bash

# Run Redis
docker run -d --rm -p 6379:6379 --name redis-demo redis

# to sh into the container
# docker exec -it redis-demo sh