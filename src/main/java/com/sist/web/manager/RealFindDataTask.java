package com.sist.web.manager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class RealFindDataTask {
	private final RealFindDataService rservice;
	@Scheduled(fixedRate = 5000)
	public void task() {
		rservice.realFindDataList();
		List<RealFindDataVO> list=DataCollection.dataCollect();
		for(RealFindDataVO vo:list)
		{
			rservice.realFindDataInsert(vo);
		}
	}
}
