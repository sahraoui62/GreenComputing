program password_finder;
uses crt;

const nb_char = 32768;

type Table = array[0..nb_char] of string;
var pass : Table;
var pass_gen : Table;
var i, j, rand, loop : integer;
const alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';

begin
clrscr;
randomize();
	writeln('Debut');
	
	loop := nb_char-1;
	{Generate a password}
	writeln('generating password...');
	for i:=1 to loop do 
	begin
		rand := random(length(alphabet));
    	pass[i-1] := alphabet[rand];
    	write(pass[i-1]);
  	end;
  	writeln();
  	writeln();
	
	{find the password}
	writeln('verifying password...');
	for i:=1 to loop do 
	begin
		for j:=1 to length(alphabet) do 
		begin
			if alphabet[j-1] = pass[i-1] then
			begin
				pass_gen[i-1] := alphabet[j-1];
				write(pass_gen[i-1]);
			end;
  		end;
  	end;
  	writeln();
  	writeln();
  	writeln('password generated successfully');
end.
