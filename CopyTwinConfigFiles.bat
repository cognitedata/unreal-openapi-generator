for /r cognite\out\twinconfig\src\org\openapitools\model\ %%f in (*.h) do copy /z %%f d:\Work\unreal-twin-renderer\Plugins\CogniteTwinConfig\Source\CogniteTwinConfig\Public\ 
for /r cognite\out\twinconfig\src\org\openapitools\model\ %%f in (*.cpp) do copy /z %%f d:\Work\unreal-twin-renderer\Plugins\CogniteTwinConfig\Source\CogniteTwinConfig\Private\