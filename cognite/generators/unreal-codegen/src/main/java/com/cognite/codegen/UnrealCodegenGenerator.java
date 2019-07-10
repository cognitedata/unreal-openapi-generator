package com.cognite.codegen;

import org.openapitools.codegen.languages.AbstractCppCodegen;
import org.openapitools.codegen.*;
import io.swagger.models.properties.*;

import java.util.*;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.util.SchemaTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.SupportingFile;
import org.openapitools.codegen.utils.ModelUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.openapitools.codegen.utils.StringUtils.camelize;

public class UnrealCodegenGenerator extends AbstractCppCodegen implements CodegenConfig {

  // source folder where to write the files
  protected String sourceFolder = "src";
  protected String documentationFolder = "doc";
  protected String apiVersion = "1.0.0";
  protected String fileNamePrefix = "CDF_API_";

  public UnrealCodegenGenerator() {
    super();


    

    // set the output folder here
    outputFolder = "generated-code/unreal-codegen";
    /**
     * Model Package.  Optional, if needed, this can be used in templates
     */
    modelPackage = "org.openapitools.model";
    /**
     * Api Package.  Optional, if needed, this can be used in templates
     */
    apiPackage = "org.openapitools.api";
    /**
     * Template Location.  This is the location which templates will be read from.  The generator
     * will use the resource stream to attempt to read the templates.
     */
    templateDir = "unreal-codegen";

    modelNamePrefix = "U" + fileNamePrefix;


    modelTemplateFiles.put("model-header.mustache", ".h");
    modelTemplateFiles.put("model-body.mustache", ".cpp");
    apiTemplateFiles.put("api-header.mustache", ".h");
    apiTemplateFiles.put("api-body.mustache", ".cpp");

   setReservedWordsLowerCase(
      Arrays.asList(
              "alignas", "alignof", "and", "and_eq", "asm", "atomic_cancel", "atomic_commit", "atomic_noexcept",
              "auto", "bitand", "bitor", "bool", "break", "case", "catch", "char", "char16_t", "char32_t",
              "class", "compl", "concept", "const", "constexpr", "const_cast", "continue", "decltype", "default",
              "delete", "do", "double", "dynamic_cast", "else", "enum", "explicit", "export", "extern", "false",
              "float", "for", "friend", "goto", "if", "inline", "int32", "import", "long", "module", "mutable",
              "namespace", "new", "noexcept", "not", "not_eq", "nullptr", "operator", "or", "or_eq", "private",
              "protected", "public", "register", "reinterpret_cast", "requires", "return", "short", "signed",
              "sizeof", "static", "static_assert", "static_cast", "struct", "switch", "synchronized", "template",
              "this", "thread_local", "throw", "true", "try", "typedef", "typeid", "typename", "union",
              "unsigned", "using", "virtual", "void", "volatile", "wchar_t", "while", "xor", "xor_eq"
      ));

    /**
     * Additional Properties.  These values can be passed to the templates and
     * are available in models, apis, and supporting files
     */
    additionalProperties.put("apiVersion", apiVersion);

    /**
     * Supporting Files.  You can write single files for the generator with the
     * entire object tree available.  If the input file has a suffix of `.mustache
     * it will be processed by the template engine.  Otherwise, it will be copied
     */
    supportingFiles.clear();
    supportingFiles.add(new SupportingFile("helpers-header.mustache", sourceFolder, "Helpers.h"));
    supportingFiles.add(new SupportingFile("helpers-body.mustache", sourceFolder, "Helpers.cpp"));
    supportingFiles.add(new SupportingFile("netclient-header.mustache", sourceFolder, "NetClient.h"));
    supportingFiles.add(new SupportingFile("netclient-body.mustache", sourceFolder, "NetClient.cpp"));
    supportingFiles.add(new SupportingFile("object.mustache", sourceFolder, "Object.h"));
    supportingFiles.add(new SupportingFile("requestinfo.mustache", sourceFolder, "RequestInfo.h"));
    supportingFiles.add(new SupportingFile("error-header.mustache", sourceFolder, "Error.h"));
    supportingFiles.add(new SupportingFile("error-body.mustache", sourceFolder, "Error.cpp"));
    supportingFiles.add(new SupportingFile("Doxyfile.mustache", documentationFolder, "Doxyfile"));
    supportingFiles.add(new SupportingFile("generateDocumentation.mustache", documentationFolder, "generateDocumentation.sh"));
    supportingFiles.add(new SupportingFile("doc-readme.mustache", documentationFolder, "README.md"));

     defaultIncludes = new HashSet<String>(
            Arrays.asList(
                    "bool",
                    "int32",
                    "int64",
                    "double",
                    "float")
    );
    languageSpecificPrimitives = new HashSet<String>(
            Arrays.asList(
                    "bool",
                    "int32",
                    "int64",
                    "double",
                    "float",
                    "FString",
                    "TArray",
                    "TMap")
    );

    super.typeMapping = new HashMap<String, String>();

    //typeMapping.put("Date", "DateTime");
    //typeMapping.put("DateTime", "DateTime");
    typeMapping.put("string", "FString");
    typeMapping.put("integer", "int32");
    typeMapping.put("float", "float");
    typeMapping.put("long", "int64");
    typeMapping.put("boolean", "bool");
    typeMapping.put("double", "double");
    typeMapping.put("array", "TArray");
    typeMapping.put("map", "TMap");
    typeMapping.put("number", "int64");
    typeMapping.put("object", "FString");
    typeMapping.put("binary", "FString");
    typeMapping.put("password", "FString");
    //TODO:Maybe use better formats for dateTime?
    typeMapping.put("file", "FString");
    typeMapping.put("DateTime", "FString");
    typeMapping.put("Date", "FString");
    typeMapping.put("UUID", "FString");
    typeMapping.put("URI", "FString");

    importMapping = new HashMap<String, String>();
  }

  /**
   * Configures the type of generator.
   * 
   * @return  the CodegenType for this generator
   * @see     org.openapitools.codegen.CodegenType
   */
  public CodegenType getTag() {
    return CodegenType.OTHER;
  }

  /**
   * Configures a friendly name for the generator.  This will be used by the generator
   * to select the library with the -g flag.
   * 
   * @return the friendly name for the generator
   */
  public String getName() {
    return "unreal-codegen";
  }


  /**
   * Returns human-friendly help for the generator.  Provide the consumer with help
   * tips, parameters here
   * 
   * @return A string value for the help message
   */
  public String getHelp() {
    return "Generates a unreal-codegen client library.";
  }
  
  /**
   * Location to write api files.  You can use the apiPackage() as defined when the class is
   * instantiated
   */
  @Override
  public String apiFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + apiPackage().replace('.', File.separatorChar);
  }
  /**
   * Location to write model files.  You can use the modelPackage() as defined when the class is
   * instantiated
   */
  public String modelFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + modelPackage().replace('.', File.separatorChar);
  }



  @Override
  public Map<String, Object> postProcessModels(Map<String, Object> objs) {
     super.postProcessModels(objs);
      return postProcessModelsEnum(objs);
  }

    @Override
    public String toEnumVarName(String value, String datatype) {
        if (value.length() == 0) {
            return "EMPTY";
        }

        String var = value.replaceAll("\\W+", "_");
        if (var.matches("\\d.*")) {
            return "_" + var;
        } else {
            return var;
        }
    }


  @Override
    public String toInstantiationType(Schema p) {
        if (ModelUtils.isMapSchema(p)) {
            return instantiationTypes.get("map");
        } else if (ModelUtils.isArraySchema(p)) {
            return instantiationTypes.get("array");
        } else {
            return null;
        }
    }

    @Override
    public String getSchemaType(Schema p) {
        String openAPIType = super.getSchemaType(p);
        String type = null;
        if (typeMapping.containsKey(openAPIType)) {
            type = typeMapping.get(openAPIType);
            if (languageSpecificPrimitives.contains(type)) {
                return toModelName(type);
            }
        } else {
            type = openAPIType;
        }
        return toModelName(type);
    }

    @Override
    public String getTypeDeclaration(Schema p) {
        String openAPIType = getSchemaType(p);
        if (languageSpecificPrimitives.contains(openAPIType)) {
            return toModelName(openAPIType);
        } else {
            return openAPIType;
        }
    }

    @Override
    public String toModelName(String type) {
        if (typeMapping.keySet().contains(type) ||
                typeMapping.values().contains(type) ||
                importMapping.values().contains(type) ||
                defaultIncludes.contains(type) ||
                languageSpecificPrimitives.contains(type)) {
            return type;
        } else {
            return getModelNamePrefix() + Character.toUpperCase(type.charAt(0)) + type.substring(1);
        }
    }

    @Override
    public String toModelImport(String name) {
        return "#include \"" + name + ".h\"";
    }

    //Might not be needed
    @Override
    public String toDefaultValue(Schema p) {
        return "";
    }


    @Override
    public String toModelFilename(String name) {
        return fileNamePrefix + camelize(name);
    }

    @Override
    public String toApiName(String name) {
        return camelize(name) + "Manager";
    }

    @Override
    public String toApiFilename(String name) {
        return camelize(name) + "Manager";
    }

    @Override
    public String toVarName(String name) {
        String paramName = name.replaceAll("[^a-zA-Z0-9_]", "");
        paramName = Character.toLowerCase(paramName.charAt(0)) + paramName.substring(1);
        if (isReservedWord(paramName)) {
            return escapeReservedWord(paramName);
        }
        return "" + paramName;
    }

    @Override
    public String toOperationId(String operationId) {
        // throw exception if method name is empty
        if (StringUtils.isEmpty(operationId)) {
            throw new RuntimeException("Empty method name (operationId) not allowed");
        }

        // method name cannot use reserved keyword, e.g. return$
        if (isReservedWord(operationId)) {
            operationId = escapeReservedWord(operationId);
        }

        // add_pet_by_id => addPetById
        return camelize(operationId, true);
    }
    /**
     * Output the Getter name for boolean property, e.g. getActive
     *
     * @param name the name of the property
     * @return getter name based on naming convention
     */
    public String toBooleanGetter(String name) {
        return name;
    }



}