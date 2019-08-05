cd cognite\generators\unreal-codegen && ^
mvn package & ^
cd ..\..\..\ && ^
rmdir /S /Q cognite\out & ^
java -cp cognite/generators/unreal-codegen/target/unreal-codegen-openapi-generator-1.0.0.jar;modules/openapi-generator-cli/target/openapi-generator-cli.jar org.openapitools.codegen.OpenAPIGenerator generate -i ./cognite/twinconfig.yaml -g unreal-codegen -o ./cognite/out

:https://storage.googleapis.com/cognitedata-api-docs/dist/v1.json
:./cognite/twinconfig.yaml

:java -jar modules/openapi-generator-cli/target/openapi-generator-cli.jar generate -i https://storage.googleapis.com/cognitedata-api-docs/dist/v1.json -g java -o cognite\out