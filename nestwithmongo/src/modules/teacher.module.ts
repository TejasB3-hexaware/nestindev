import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { TeacherController } from '../controllers/teacher.controller';
import { TeacherService } from '../services/teacher.service';
import { TeacherRepo } from '../repository/teacher.repo';
import { Teacher, TeacherSchema } from '../schemas/teacher.schema';

@Module({
    imports: [
        MongooseModule.forFeature([{ name: Teacher.name, schema: TeacherSchema }])
    ],
    controllers: [TeacherController],
    providers: [TeacherService, TeacherRepo],
    exports: [TeacherService, TeacherRepo]
  })
  export class TeacherModule { }