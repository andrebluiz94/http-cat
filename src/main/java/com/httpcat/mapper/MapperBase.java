package com.httpcat.mapper;

public interface MapperBase<Source, Target> {
	void map(Source source, Target target);
	Target map(Source source);
}
