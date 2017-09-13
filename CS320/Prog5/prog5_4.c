#include <stdio.h>
#include <string.h>
#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>

/*
 * Assignment #5-4, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
 * This file acts as a simple Lua interpreter. It will take input from standard
 * in. It will then attempt to do multiple-lined for do end, if then else
 * statements.
 */
    
int main(int argc, char **argv) {
      	lua_State *L = luaL_newstate();   //l opens Lua 
      	luaL_openlibs(L);
      	printf(
      	"Assignment #5-4, Paul Truong Nguyen, paul.truong.nguyen@gmail.com\n");
      	printf("Lua 5.3.3 Copyright (C) 1994-2016 Lua.org, PUC-Rio\n");
      	
      	char buff[256];
      	int error;
      	while (fgets(buff, sizeof(buff), stdin) != NULL) {

        	luaL_loadstring(L, buff);
        	lua_pcall(L, 0, 0, 0);
        	
      	}
      	
      	lua_close(L);
      	return 0;
}
