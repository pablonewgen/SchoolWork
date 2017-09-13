#include <stdio.h>
#include <string.h>
#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>

/*
 * Assignment #5-1, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
 * This file acts as a simple Lua interpreter. It will take in a file
 * from the command line, execute the .lua file and then close the lua
 * state. 
 */
    
int main(int argc, char **argv) {
      	lua_State *L = luaL_newstate();   // opens Lua 
      	luaL_openlibs(L);		  // opens Lua libraries 
      	
      	printf(
      	"Assignment #5-1, Paul Truong Nguyen, paul.truong.nguyen@gmail.com\n");
      	char *filename = argv[1];	  // stores filename from command line
	luaL_dofile(L, filename);
      
      	lua_close(L);
      	return 0;
}
