import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Teacher, TeacherDocument } from 'src/schemas/teacher.schema';

@Injectable()
export class TeacherRepo {
    constructor(
        @InjectModel(Teacher.name)
        private readonly teacherModel: Model<TeacherDocument>) {}

    async create(data): Promise<Teacher> {
        return new this.teacherModel(data).save();
    }

    async findAll(): Promise<Teacher[]> {
        return this.teacherModel.find({})
            .exec();
    }

    async update(teacherId, data): Promise<Teacher> {
        const filter = { _id: teacherId };
        return this.teacherModel.findOneAndUpdate(filter, data);
    }

    async delete(teacherId): Promise<Teacher> {
        const filter = { _id: teacherId };
        return this.teacherModel.findByIdAndDelete(teacherId);
    }
}