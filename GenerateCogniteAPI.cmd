cd cognite\generators\unreal-codegen && ^
mvn package & ^
cd ..\..\..\ && ^
rmdir /S /Q cognite\out & ^
java -cp cognite/generators/unreal-codegen/target/unreal-codegen-openapi-generator-1.0.0.jar;modules/openapi-generator-cli/target/openapi-generator-cli.jar org.openapitools.codegen.OpenAPIGenerator generate -i ./cognite/twinconfig.yaml -g unreal-codegen -o ./cognite/out

