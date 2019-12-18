#!/bin/bash

# Upload an Asset(Ex: test.png)
# curl -u admin:admin -X POST -F file=@"test.png" http://localhost:4502/content/dam/geometrixx/portraits.createasset.html

AEM_URL=http://admin:admin@localhost:4502
DESTINATION_FOLDER=/content/dam/projects

curl -X POST -F file=@"test.png" $AEM_URL$DESTINATION_FOLDER.createasset.html
