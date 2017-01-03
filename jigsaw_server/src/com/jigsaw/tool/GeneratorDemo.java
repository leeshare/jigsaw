package com.jigsaw.tool;

import javax.sql.DataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * GeneratorDemo
 */
public class GeneratorDemo {
	
	public static DataSource getDataSource() {
		Prop p = PropKit.use("a_little_config.txt");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model ��ʹ�õİ���
		String baseModelPackageName = "com.jigsaw.model.base";
		// base model �ļ�����·��
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/jigsaw/model/base";
		
		// model ��ʹ�õİ��� (MappingKit Ĭ��ʹ�õİ���)
		String modelPackageName = "com.jigsaw.model";
		// model �ļ�����·�� (MappingKit �� DataDictionary �ļ�Ĭ�ϱ���·��)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// ����������
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// �������ݿⷽ��
		gernerator.setDialect(new MysqlDialect());
		// ��Ӳ���Ҫ���ɵı���
		gernerator.addExcludedTable("adv");
		// �����Ƿ��� Model ������ dao ����
		gernerator.setGenerateDaoInModel(true);
		// �����Ƿ������ֵ��ļ�
		gernerator.setGenerateDataDictionary(false);
		// ������Ҫ���Ƴ��ı���ǰ׺��������modelName��������� "osc_user"���Ƴ�ǰ׺ "osc_"�����ɵ�model��Ϊ "User"���� OscUser
		gernerator.setRemovedTableNamePrefixes("t_");
		// ����
		gernerator.generate();
	}
}




