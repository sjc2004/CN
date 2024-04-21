ip=[x for x in "5.4.5.4".split('.')]
 
 
#print(ip)
map={'A':['A',1],'B':['B',2], 'C':['C',3],'D':['D',4],'E':['E', 4],"Fail":['Invalid',-1]}
class_details=map['A' if int(ip[0])<128 else 'B' if int(ip[0])<192 else 'C' if int(ip[0])<224 else 'D' if int(ip[0])<240 else 'E' if int(ip[0])<266 else "Fail"]
#print(class_details)
print("Class:"+class_details[0]+  (("\nNetwork ID "+ ".".join(ip[0:class_details[1]]) +  (("\nHost ID "+".".join(ip[class_details[1]:]))) if class_details[1]!=4 else "") if class_details[0]!='Invalid' else ""))
 
ip='106.255.255.6/20'
ip,mask_size=ip.split("/")
ip_split=[bin(int(x)).replace("0b",'').rjust(8,'0') for x in ip.split(".")]
 
print("IP on split:",ip_split)
mask=int(mask_size)
print(mask)
host_id= ('.'.join([str(int(x,2)) for x in ip_split[0:mask//8]]))+'.'+str(int((ip_split[mask//8][0:mask%8]).ljust(8,'0'),2))
print("Host Id:",host_id)
#print(ip_split)
 
#print((ip_split[mask//8][mask%8::]),2)
network_id= str(int((ip_split[mask//8][mask%8::]),2))+'.'+('.'.join([str(int(x,2)) for x in ip_split[mask//8+1::]]))
print("Network ID:",network_id)
