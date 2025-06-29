# Smart Fitness System - AI-Powered Motion Analysis

## 项目概述

智能健身系统是一个综合性的健身应用程序，使用基于OpenCV的运动分析技术提供个性化的训练推荐和实时形体纠正。该系统解决了传统健身程序缺乏个性化的痛点，通过AI技术为用户提供智能化的健身体验。

## 核心特性

### 🎯 AI运动分析
- **实时姿态检测**: 使用OpenCV进行实时人体姿态识别
- **动作形体评分**: 智能分析运动姿势的正确性
- **实时反馈**: 提供即时的形体纠正建议
- **运动相位识别**: 自动识别动作的向心、离心和等长阶段

### 🏋️ 智能训练管理
- **个性化训练计划**: 基于用户数据生成定制化训练方案
- **运动模板系统**: 可保存和复用成功的训练模式
- **进度跟踪**: 全面记录训练数据和身体指标
- **适应性调整**: 根据表现自动调整训练强度

### 📊 数据分析与进度追踪
- **多维度进度分析**: 力量、耐力、柔韧性等全方位数据追踪
- **可视化报告**: 直观的图表展示训练效果
- **目标管理**: 设定并追踪个人健身目标
- **历史数据分析**: 长期趋势分析和预测

### 💬 实时通信
- **WebSocket支持**: 实时动作分析和反馈
- **即时通知**: 训练提醒和成就通知
- **社交功能**: 训练分享和社区互动

## 技术架构

### 后端技术栈
- **框架**: Spring Boot 3.2.0
- **数据库**: PostgreSQL 
- **安全**: Spring Security + JWT认证
- **实时通信**: WebSocket + STOMP
- **数据访问**: Spring Data JPA + Hibernate
- **数据迁移**: Flyway
- **缓存**: Caffeine
- **构建工具**: Gradle

### AI与计算机视觉
- **OpenCV**: 图像处理和姿态检测
- **运动分析**: 关节角度计算、运动轨迹分析
- **机器学习**: 运动质量评估算法
- **实时处理**: 高效的视频帧处理

### 数据模型设计

#### 核心实体关系
```
User (用户)
├── WorkoutSession (训练会话)
│   ├── WorkoutExercise (训练动作)
│   │   └── ExerciseSet (训练组数)
│   └── MotionAnalysisResult (动作分析结果)
├── UserProgress (进度记录)
└── Exercise (动作库)
```

#### 关键特性
- **审计功能**: 所有实体包含创建/更新时间戳
- **乐观锁**: 使用版本控制防止并发冲突
- **软删除**: 保持数据完整性
- **索引优化**: 针对查询性能优化的数据库索引

## API接口设计

### 认证端点
```http
POST /api/auth/register     # 用户注册
POST /api/auth/login        # 用户登录  
POST /api/auth/refresh      # 刷新token
```

### 训练管理
```http
GET    /api/workouts                    # 获取训练列表
POST   /api/workouts                    # 创建训练会话
POST   /api/workouts/{id}/start         # 开始训练
POST   /api/workouts/{id}/complete      # 完成训练
GET    /api/workouts/templates          # 获取训练模板
```

### 运动分析
```http
GET    /api/analysis/session/{id}/stats # 获取会话分析统计
POST   /api/analysis/frame              # 上传帧进行分析
```

### WebSocket端点
```
/ws/motion-analysis     # 实时动作分析
/ws/workout-tracking    # 训练状态跟踪
```

## 安全机制

### JWT认证体系
- **访问令牌**: 24小时有效期，用于API访问
- **刷新令牌**: 7天有效期，用于令牌更新
- **角色权限**: USER、TRAINER、ADMIN三级权限控制

### 数据安全
- **密码加密**: BCrypt强加密存储
- **HTTPS**: 传输层安全加密
- **CORS配置**: 跨域访问控制
- **SQL注入防护**: JPA参数化查询

## 运动分析算法

### 姿态检测流程
1. **帧预处理**: 图像格式转换和噪声过滤
2. **关键点检测**: 识别人体34个关键点位置
3. **骨架构建**: 连接关键点形成人体骨架模型
4. **角度计算**: 计算关节角度和运动范围
5. **形体评分**: 基于预定义标准评估动作质量

### 评分算法
```java
// 综合评分算法示例
double overallScore = (
    formScore * 0.4 +           // 动作标准性 40%
    symmetryScore * 0.2 +       // 左右对称性 20%
    stabilityScore * 0.2 +      // 动作稳定性 20%
    tempoScore * 0.1 +          // 节奏控制 10%
    depthScore * 0.1            // 动作幅度 10%
);
```

## 数据库设计

### 主要表结构
- **users**: 用户基本信息和偏好设置
- **exercises**: 运动动作库，包含OpenCV分析参数
- **workout_sessions**: 训练会话记录
- **motion_analysis_results**: AI分析结果存储
- **user_progress**: 用户进度跟踪数据

### 性能优化
- **复合索引**: 针对常用查询组合优化
- **分区表**: 历史数据分区存储
- **连接池**: HikariCP高性能连接池
- **查询优化**: JPA查询调优和N+1问题解决

## 部署与运行

### 环境要求
- Java 17+
- PostgreSQL 12+
- 4GB+ RAM
- OpenCV支持的操作系统

### 快速启动
```bash
# 克隆项目
git clone [repository-url]
cd smart-fitness-system/backend

# 配置数据库
createdb fitness_db

# 运行应用
./gradlew bootRun
```

### 配置文件
```yaml
# application.yml 关键配置
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fitness_db
    username: ${DB_USERNAME:fitness_user}
    password: ${DB_PASSWORD:fitness_pass}
  
fitness:
  jwt:
    secret: ${JWT_SECRET:your-secret-key}
    expiration: 86400000
  opencv:
    confidence-threshold: 0.7
```

## 监控与运维

### 应用监控
- **Spring Boot Actuator**: 健康检查和指标监控
- **Prometheus**: 指标收集和告警
- **日志聚合**: 结构化日志记录

### 性能指标
- **API响应时间**: < 200ms (P95)
- **动作分析延迟**: < 100ms
- **数据库连接池**: 监控连接使用率
- **内存使用**: JVM堆内存监控

## 扩展性设计

### 水平扩展
- **无状态设计**: 支持多实例部署
- **数据库读写分离**: 主从复制支持
- **缓存层**: Redis分布式缓存

### 模块化架构
- **服务层解耦**: 业务逻辑模块化
- **插件式运动算法**: 可扩展的分析算法
- **多租户支持**: 企业级多组织架构

## 开发指南

### 代码规范
- **命名约定**: 驼峰命名法，语义化命名
- **注释标准**: JavaDoc完整文档
- **异常处理**: 统一异常处理机制
- **测试覆盖**: 单元测试和集成测试

### 贡献流程
1. Fork项目仓库
2. 创建功能分支
3. 提交代码变更
4. 创建Pull Request
5. 代码审查通过后合并

## 路线图

### 即将发布
- [ ] 运动推荐算法优化
- [ ] 移动端适配
- [ ] 社交功能增强
- [ ] 营养计划集成

### 长期规划
- [ ] VR/AR健身体验
- [ ] 多语言国际化
- [ ] 企业版功能
- [ ] 第三方设备集成

## 许可证

本项目采用 MIT 许可证。详见 [LICENSE](LICENSE) 文件。

## 联系方式

- 项目维护: [maintainer@email.com]
- 技术支持: [support@email.com]
- 功能建议: [feedback@email.com]

---

**智能健身系统 - 让科技赋能健康生活** 🏃‍♂️💪

通过AI技术革新传统健身体验，为用户提供个性化、智能化的健身解决方案。 