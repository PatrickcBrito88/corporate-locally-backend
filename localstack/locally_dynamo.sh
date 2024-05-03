aws dynamodb create-table \
    --table-name corporate-locally \
    --attribute-definitions \
        AttributeName=periodoRequest,AttributeType=S \
        AttributeName=idRequest,AttributeType=S \
    --key-schema \
        AttributeName=periodoRequest,KeyType=HASH \
        AttributeName=idRequest,KeyType=RANGE \
    --billing-mode PAY_PER_REQUEST \
    --tags \
        Key=projeto,Value=corporate-locally \
        Key=tipo_aplicacao,Value=lambda:java \
        Key=ambiente,Value=local \
    --endpoint-url http://localhost:4566 \
    --region us-east-1