import com.github.zuihoou.generator.CodeGenerator;
import com.github.zuihoou.generator.config.CodeGeneratorConfig;
import com.github.zuihoou.generator.config.FileCreateConfig;
import com.github.zuihoou.generator.type.EntityFiledType;
import com.github.zuihoou.generator.type.EntityType;
import com.github.zuihoou.generator.type.GenerateType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试代码生成权限系统的代码
 *
 * @author zuihou
 * @date 2019/05/25
 */
public class TestMsgsGenerator {
    /***
     * 注意，想要在这里直接运行，需要手动增加 mysql 驱动
     * @param args
     */
    public static void main(String[] args) {
//        CodeGeneratorConfig build = buildSmsEntity();
        CodeGeneratorConfig build = buildMsgsEntity();

        build.setUsername("root");
        build.setPassword("root");
        System.out.println("输出路径：");
        System.out.println(System.getProperty("user.dir") + "/zuihou-backend/zuihou-msgs");
        build.setProjectRootPath(System.getProperty("user.dir") + "/zuihou-backend/zuihou-msgs");

        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEnum(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateDto(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateXml(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
        fileCreateConfig.setGenerateController(GenerateType.IGNORE);
        build.setFileCreateConfig(fileCreateConfig);

        //手动指定枚举类 生成的路径
        Set<EntityFiledType> filedTypes = new HashSet<>();
        filedTypes.addAll(Arrays.asList(
                EntityFiledType.builder().name("providerType").table("sms_template")
                        .packagePath("com.github.zuihou.sms.enumeration.ProviderType").gen(GenerateType.IGNORE).build()
        ));
        build.setFiledTypes(filedTypes);
        CodeGenerator.run(build);
    }


    public static CodeGeneratorConfig buildSmsEntity() {
        List<String> tables = Arrays.asList(
//                "sms_template"
                "sms_task"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("msgs", "sms", "zuihou", "", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    private static CodeGeneratorConfig buildMsgsEntity() {
        List<String> tables = Arrays.asList(
                "msgs_center_info",
                "msgs_center_info_receive"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("msgs", "", "zuihou", "", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }
}
