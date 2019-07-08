# file-output.py
import sys

if __name__ == "__main__":
	print('abc')
	f = open(sys.argv[1],'w')
	f.write(sys.argv[2])
	f.close()
	
