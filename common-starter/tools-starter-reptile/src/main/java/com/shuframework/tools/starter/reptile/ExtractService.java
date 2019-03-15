package com.shuframework.tools.starter.reptile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author shu
 * 
 */
public class ExtractService {
	/**
	 * @param rule
	 * @return
	 */
	public static List<LinkTypeData> extract(Rule rule) {

		// 进行对rule的必要校验
		validateRule(rule);

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		LinkTypeData data = null;
		Elements results = getElements(rule);
		System.out.println(results);
		for (Element result : results) {
			Elements links = result.getElementsByTag("a");

			for (Element link : links) {
				// 必要的筛选
				String linkHref = link.attr("href");
				String linkText = link.text();

				data = new LinkTypeData();
				data.setLinkHref(linkHref);
				data.setLinkText(linkText);

				datas.add(data);
			}
		}

		return datas;
	}

	public static Elements getElements(Rule rule) {
		String url = rule.getUrl();
		String[] params = rule.getParams();
		String[] values = rule.getValues();
		String resultTagName = rule.getResultTagName();
		int type = rule.getType();
		int requestType = rule.getRequestMoethod();

		Connection conn = Jsoup.connect(url);
		// 设置查询参数

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				conn.data(params[i], values[i]);
			}
		}

		// 设置请求类型
		Document doc = null;
		try {
			switch (requestType) {
				case Rule.GET:
					doc = conn.timeout(100000).get();
					break;
				case Rule.POST:
					doc = conn.timeout(100000).post();
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 处理返回数据
		Elements results = new Elements();
		switch (type) {
		case Rule.CLASS:
			results = doc.getElementsByClass(resultTagName);
			break;
		case Rule.ID:
			Element result = doc.getElementById(resultTagName);
			results.add(result);
			break;
		case Rule.SELECTION:
			results = doc.select(resultTagName);
			break;
		default:
			// 当resultTagName为空时默认去body标签
			if (("".equals(resultTagName) || resultTagName == null)) {
				results = doc.getElementsByTag("body");
			}
		}
		return results;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if ("".equals(url) || url == null) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}

}