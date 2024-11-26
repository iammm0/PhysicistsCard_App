职责：

网络请求的管理：API层的主要任务是处理所有与远程服务器的通信。它负责发送网络请求、接收响应数据、处理网络错误，以及将数据转换为传输模型。
数据传输模型：API层使用数据传输模型（DTOs）来描述从服务端接收的数据结构。这些模型只反映远程API的请求和响应格式。
依赖注入：使用Koin等依赖注入框架将API接口注入到服务层。
具体处理内容：

构建和发送HTTP请求（GET、POST、PUT、DELETE等）。
处理HTTP响应，并将其转换为相应的数据传输模型。
捕获和处理网络错误，如超时、请求失败等，通常会返回一些通用的错误对象或状态码。
不包含业务逻辑，只处理与API交互的内容。