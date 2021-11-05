package kr.ac.yonsei.home.VO;

import org.apache.ibatis.type.Alias;

@Alias("CodeVO")
public class CodeVO {
	private String cd;
	private String cdName;
	private String cdType;
	
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public String getCdType() {
		return cdType;
	}
	public void setCdType(String cdType) {
		this.cdType = cdType;
	}
	
	
}
