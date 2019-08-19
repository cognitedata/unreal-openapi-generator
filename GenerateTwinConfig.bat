cd cognite\generators\twinconfig-codegen && ^
mvn package & ^
cd ..\..\..\ && ^
rmdir /S /Q cognite\out & ^
java -cp cognite/generators/twinconfig-codegen/target/twinconfig-codegen-openapi-generator-1.0.0.jar;modules/openapi-generator-cli/target/openapi-generator-cli.jar org.openapitools.codegen.OpenAPIGenerator generate -i ./cognite/twinconfig.yaml -g twinconfig-codegen -o ./cognite/out/twinconfig && ^
:for /r cognite\out\twinconfig\src\org\openapitools\model\ %%f in (*.h) do copy /y %%f d:\Work\unreal-twin-renderer\Plugins\CogniteTwinConfig\Source\CogniteTwinConfig\Public\ & ^
:for /r cognite\out\twinconfig\src\org\openapitools\model\ %%f in (*.cpp) do copy /y %%f d:\Work\unreal-twin-renderer\Plugins\CogniteTwinConfig\Source\CogniteTwinConfig\Private\



:java -cp twinconfig-codegen-openapi-generator-1.0.0.jar;openapi-generator-cli.jar org.openapitools.codegen.OpenAPIGenerator generate -i ./twinconfig.yaml -g twinconfig-codegen -o ./out/twinconfig