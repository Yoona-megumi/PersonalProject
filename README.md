| 软件工程 |[网工1934](https://edu.cnblogs.com/campus/gdgy/networkengineering1934-Softwareengineering) |
| ----------------- |--------------- |
| <div style="width: 610pt">作业要求:<br>1、在Github仓库中新建一个学号为名的文件夹。<br>2、在开始实现程序之前，在PSP表格记录下你估计在程序开发各个步骤上耗费的时间，在你实现程序之后，在PSP表格记录下你在程序的各个模块上实际花费的时间。<br>3、编程语言不限，将编译好的程序发布到Github仓库中的releases中<br>4、提交的代码要求经过Code Quality Analysis工具的分析并消除所有的警告。<br>5、完成项目的首个版本之后，请使用性能分析工具Studio Profiling Tools来找出代码中的性能瓶颈并进行改进。<br>6、使用Github[附录3]来管理源代码和测试用例，代码有进展即签入Github。签入记录不合理的项目会被助教抽查询问项目细节。<br>7、使用单元测试[附录4]对项目进行测试，并使用插件查看测试分支覆盖率等指标；写出至少10个测试用例确保你的程序能够正确处理各种情况。| [作业要求](https://edu.cnblogs.com/campus/gdgy/networkengineering1934-Softwareengineering/homework/12137) |
| 作业目标:<br>设计一个论文查重算法，给出一个原文文件和一个在这份原文上经过了增删改的抄袭版论文的文件，在答案文件中输出其重复率。 |  

[博客园链接](https://www.cnblogs.com/ozx5391/p/15268095.html)

<font size=5>**一、PSP表格**</font>
| **PSP2.1**                       | **Personal Software Process Stages** | **预估耗时（分钟）** | **实际耗时（分钟）** |
| --------------------------------------- | ------------------------------------------ | -------------------------- | -------------------------- |
| **Planning**                            | **计划**                                   |                            |                            |
| · Estimate                              | · 估计这个任务需要多少时间                   | 1390                       | 1650                       |
| **Development**                         | **开发**                                   |                            |                            |
| · Analysis                              | · 需求分析 (包括学习新技术)                 | 250                         | 300                       |
| · Design Spec                           | · 生成设计文档                             | 60                          | 120                       |
| · Design Review                         | · 设计复审                                 | 30                         | 20                         |
| · Coding Standard                       | · 代码规范 (为目前的开发制定合适的规范)      | 60                         | 30                         |
| · Design                                | · 具体设计                                 | 120                        | 180                        |
| · Coding                                | · 具体编码                                 | 450                        | 600                        |
| · Code Review                           | · 代码复审                                 | 120                        | 80                         |
| · Test                                  | · 测试（自我测试，修改代码，提交修改）        | 120                        | 210                       |
| **Reporting**                           | **报告**                                   |                            |                           |
| · Test Repor                            | · 测试报告                                 | 40                         |                            |
| · Size Measurement                      | ·计算工作量                                | 20                         | 30                         |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划               | 60                         | 80                         |
| ·                                       | · 合计                                     | 1390                       | 1650                       |

<br>

<font size=5>**二、计算模块接口与实现**</font>

<font size=3>**1、主要实现类**</font>

主类：

PaperCheck：main方法接受命令行传入的数据，调用其他工具类进行文件的输入输出、计算simhash值以及海明距离，最后结束程序的运行。

工具类：

IOText：文件读取工具类，控制文件的读入和输出。

Simhash：计算simhash值工具类，传入IOText类读取的字符型数据，计算文件的simhash值。

Hamming：计算海明距离工具类，传入两个文件的simhash值，计算两文件的海明距离。

外部依赖：com.hankcs.hanlp（汉语言处理包）

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914191914169-878436277.png)<br>
项目结构：
![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914175613169-694326545.png)

<br>

<font size=3>**2、关键函数的分析与实现**</font>

分析：实现论文查重的关键在于相似度算法，在查询网上资料后，我决定使用simhash+海明距离算法。

实现：
![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914181643273-1618199175.png)

看不懂？没有关系，我帮你简化一下：

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914182746329-1434168179.png)

simhash算法分为5个步骤：分词、hash、加权、合并、降维，具体过程如下所述：

（1）分词
&ensp;&ensp;&ensp;&ensp;&ensp;给定一段语句，进行分词，得到有效的特征向量，然后为每一个特征向量设置1-5等5个级别的权重（如果是给定一个文本，那么特征向量可以是文本中的词，其权重可以是这个词出现的次数）。例如给定一段语句：“CSDN博客结构之法算法之道的作者July”，分词后为：“CSDN 博客 结构 之 法 算法 之 道 的 作者 July”，然后为每个特征向量赋予权值：CSDN(4) 博客(5) 结构(3) 之(1) 法(2) 算法(3) 之(1) 道(2) 的(1) 作者(5) July(5)，其中括号里的数字代表这个单词在整条语句中的重要程度，数字越大代表越重要。

（2）hash
&ensp;&ensp;&ensp;&ensp;&ensp;通过hash函数计算各个特征向量的hash值，hash值为二进制数01组成的n-bit签名。比如“CSDN”的hash值Hash(CSDN)为100101，“博客”的hash值Hash(博客)为“101011”。就这样，字符串就变成了一系列数字。

（3）加权
&ensp;&ensp;&ensp;&ensp;&ensp;在hash值的基础上，给所有特征向量进行加权，即W = Hash * weight，且遇到1则hash值和权值正相乘，遇到0则hash值和权值负相乘。例如给“CSDN”的hash值“100101”加权得到：W(CSDN) = 100101 4 = 4 -4 -4 4 -4 4，给“博客”的hash值“101011”加权得到：W(博客)=101011 5 = 5 -5 5 -5 5 5，其余特征向量类似此般操作。

（4）合并
&ensp;&ensp;&ensp;&ensp;&ensp;将上述各个特征向量的加权结果累加，变成只有一个序列串。拿前两个特征向量举例，例如“CSDN”的“4 -4 -4 4 -4 4”和“博客”的“5 -5 5 -5 5 5”进行累加，得到“4+5 -4+-5 -4+5 4+-5 -4+5 4+5”，得到“9 -9 1 -1 1”。

（5）降维
&ensp;&ensp;&ensp;&ensp;&ensp;对于n-bit签名的累加结果，如果大于0则置1，否则置0，从而得到该语句的simhash值，最后我们便可以根据不同语句simhash的海明距离来判断它们的相似度。例如把上面计算出来的“9 -9 1 -1 1 9”降维（某位大于0记为1，小于0记为0），得到的01串为：“1 0 1 0 1 1”，从而形成它们的simhash签名。



具体内容可参考：
>[simhash算法原理](https://blog.csdn.net/laobai1015/article/details/78011870?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.no_search_link&spm=1001.2101.3001.4242)

<br>

<font size=5>**三、计算模块接口部分的性能改进**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914184029131-1270349355.png)

从上图可以看出调用次数最多的是com.hankcs.hanlp包提供的接口， 即分词、取关键词与计算词频花费了最多的时间。

<br>

<font size=5>**四、模块部分单元测试展示**</font>

<font size=3>**1、Simhash类测试**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914184829781-826683638.png)

测试结果截图：

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914184937249-293854191.png)

<font size=3>**2、Hamming类测试**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914184717116-708103760.png)

测试结果截图：

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914184854406-1623355664.png)

<font size=3>**3、Papercheck类测试**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914185126347-686206673.png)

测试结果截图：

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914185152562-1586808988.png)

<font size=3>**4、代码覆盖率**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914185708276-1054079482.png)

（有些异常没有发生，catch代码没有运行）

<br>

<font size=5>**五、模块部分异常处理说明**</font>

通过测试不难发现：当文件为空的时候，程序会报错

因此我们可以在读完文件后加一个判断条件

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914190200810-1419593534.png)

<br>

<font size=5>**六. 项目程序功能测试**</font>

![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914191014099-1593971409.png)

运行结果截图：
![](https://img2020.cnblogs.com/blog/2534361/202109/2534361-20210914191244898-1843420697.png)
