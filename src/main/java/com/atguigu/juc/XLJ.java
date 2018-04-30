package com.atguigu.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor //有参
@NoArgsConstructor	//空参
@Data				//get，set
@Accessors(chain=true)			//链式
public class XLJ {

//	private @Getter @Setter String name;
	private String name;
	private double age;
}

