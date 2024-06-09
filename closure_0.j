.class public closure_0
.super java/lang/Object
.implements fun_unit_bool_unit
.field public sl Lframe_0;
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

.method public apply(Z)V
.limit locals 5
.limit stack 6
new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 0
getfield closure_0/sl Lframe_0;
putfield frame_1/sl Lframe_0;
dup
iload 1
putfield frame_1/loc_1 Z
astore 0
L1:
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 0
getfield frame_1/loc_1 Z
invokevirtual java/io/PrintStream/println(Z)V
aload 0
getfield frame_1/loc_1 Z
sipush 1
iand
ifeq L3
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 0
getfield frame_1/loc_1 Z
invokevirtual java/io/PrintStream/println(Z)V
L3:
return
.end method