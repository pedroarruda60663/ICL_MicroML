.class public Demo
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
.method public static main([Ljava/lang/String;)V
 .limit locals 10
 .limit stack 256
L0:
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore 0
aload 0
new ref_int
dup
invokespecial ref_int/<init>()V
dup
sipush 1
putfield ref_int/value I
putfield frame_0/loc_0 Lref_int;
sipush 2
sipush 1
if_icmpgt L3
sipush 0
goto L4
L3:
sipush 1
L4:
ifeq L1
aload 0
getfield frame_0/loc_0 Lref_int;
sipush 2
setfield ref_int/value I
goto L2
L1:
aload 0
getfield frame_0/loc_0 Lref_int;
sipush 1
setfield ref_int/value I
L2:
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
