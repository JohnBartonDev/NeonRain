package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;

public class VBPerformanceTimer {

	private static ObjectMap<String, Counter> _counters = new ObjectMap<>();
	
	public static void start(String name) {
		Counter counter = getCounter(name);
		counter.start();
	}
	
	public static void end(String name) {
		if(!_counters.containsKey(name)) {
			Gdx.app.error(VBPerformanceTimer.class.getSimpleName(), "No such counter");
			return;
		}
		_counters.get(name).end();
	}
	
	public static void average(String name) {
		if(!_counters.containsKey(name)) {
			Gdx.app.error(VBPerformanceTimer.class.getSimpleName(), "No such counter");
			return;
		}
		_counters.get(name).getAverage();
	}
	
	private static Counter getCounter(String name) {
		Counter counter;
		
		if(_counters.containsKey(name)) {
			counter = _counters.get(name);
		}
		else {
			counter = new Counter(name, 10);
			_counters.put(name, counter);
		}
		return counter;
	}
	
	private static class Counter{
		private int _iteration = -1;
		private int _max;
		private long[] _startTimes;
		private long[] _totalIterationTime;
		private String _name;
		
		public Counter(String name, int max) {
			_startTimes = new long[max];
			_totalIterationTime = new long[max];
			_name = name;
			_max = max;
		}
		
		public void start() {
			if(_iteration == _max - 1) _iteration = -1;
			_startTimes[++_iteration] = TimeUtils.nanoTime();
		}
		
		public void end() {
			long totalIterationTime = TimeUtils.nanoTime() - _startTimes[_iteration];
			int iteration = _iteration + 1;
			float elapsed = totalIterationTime / 1000000f;
			_totalIterationTime[_iteration] = totalIterationTime;
			Gdx.app.log(VBPerformanceTimer.class.getSimpleName(), _name + " Iteration:" + iteration +  " Elapsed:" + elapsed);
		}
		
		public void getAverage() {
			long sum = 0;
			for(int i = 0, end = _iteration + 1; i < end; i++) {
				sum += _totalIterationTime[i];
			}
			
			long mean = sum / (_iteration + 1);
			float toMilli = mean / 1000000f;
		
			Gdx.app.log(VBPerformanceTimer.class.getSimpleName(), _name + " Mean:" + toMilli);
		}
	}
}
