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
sipush 2
putfield frame_0/loc_0 I
aload 0
sipush 10
putfield frame_0/loc_1 I
aload 0
getfield frame_0/loc_1 I
aload 0
getfield frame_0/loc_0 I
if_icmpgt L3
sipush 0
goto L4
L3:
sipush 1
L4:
ifeq L2
new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 0
putfield frame_1/sl Lframe_0;
astore 0
aload 0
sipush 2
putfield frame_1/loc_0 I
aload 0
getfield frame_1/loc_0 I
sipush 10
imul
dup
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokevirtual java/io/PrintStream/println(I)V
aload 0
getfield frame_1/sl Lframe_0;
astore 0
pop
L2:
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
